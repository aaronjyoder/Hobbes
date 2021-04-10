package com.aaronjyoder.hobbes.bot.commands;

import com.aaronjyoder.hobbes.bot.Command;
import com.aaronjyoder.hobbes.bot.input.MessageInput;
import com.aaronjyoder.hobbes.bot.input.SlashInput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SplittableRandom;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import net.dv8tion.jda.api.EmbedBuilder;
import org.jetbrains.annotations.NotNull;

public class Roll extends Command {

  /**
   * Syntax Examples A - amount of dice X - number of sides C - some number AdX dX Ad AdX+C AdX-C dX+C dX-C Ad+C Ad-C
   */

  private static final Pattern simpleRollPattern = Pattern.compile("(\\d+[d|D]\\d+|\\d+[d|D]|[d|D]\\d+)");
  private static final Pattern genericRollPattern = Pattern.compile(simpleRollPattern.pattern() + "((dl|dh|kl|kh|[+-/dk])(\\d+))?");
  private static final Pattern addRollPattern = Pattern.compile(simpleRollPattern.pattern() + "(([+])(\\d+))");
  private static final Pattern subtractRollPattern = Pattern.compile(simpleRollPattern.pattern() + "(([-])(\\d+))");
  private static final Pattern divideRollPattern = Pattern.compile(simpleRollPattern.pattern() + "(([/])(\\d+))");
  private static final Pattern dropLowestRollPattern = Pattern.compile(simpleRollPattern.pattern() + "((dl|[d])(\\d+))");
  private static final Pattern dropHighestRollPattern = Pattern.compile(simpleRollPattern.pattern() + "((dh)(\\d+))");
  private static final Pattern keepLowestRollPattern = Pattern.compile(simpleRollPattern.pattern() + "((kl)(\\d+))");
  private static final Pattern keepHighestRollPattern = Pattern.compile(simpleRollPattern.pattern() + "((kh|[k])(\\d+))");

  public Roll() {
    settings.setAliases("roll");
    settings.setDescription("Rolls dice based on input.");
  }

  @Override
  protected void execute(SlashInput input) {

  }

  @Override
  protected void execute(MessageInput input) {
    try {
      List<Dice> diceList = parse(input.argString());

      EmbedBuilder embedBuilder = settings.embedBuilder();
      if (diceList.size() > 1) {
        embedBuilder.setTitle("Dice Rolls");
      } else {
        embedBuilder.setTitle("Dice Roll");
      }
      embedBuilder.setDescription(diceList.stream().map(Object::toString).collect(Collectors.joining("\n")));
      embedBuilder.setFooter("TEST OUTPUT");

      input.event().getChannel().sendMessage(embedBuilder.build()).queue();
    } catch (Exception e) {
      StringBuilder sb = new StringBuilder();
      sb.append("Invalid input. Please use proper D&D syntax and separate your dice rolls with a reasonable delimiter.").append("\n");
      sb.append("Also, please don't use numbers that are too large.").append("\n\n");
      if (e.getMessage() != null) {
        sb.append("__**Exception Details**__").append("\n");
        sb.append(e.getMessage());
      }
      input.event().getChannel().sendMessage(sb.toString()).queue();
    }
  }

  private List<Dice> parse(String inputStr) { // Input string should have no spaces whatsoever.
    List<Dice> diceList = new ArrayList<>();
    StringTokenizer tokenizer = new StringTokenizer(inputStr, ",|:;\s");

    Matcher m;
    while (tokenizer.hasMoreTokens()) {
      m = genericRollPattern.matcher(tokenizer.nextToken());
      if (m.find()) {
        diceList.add(parseDice(m.group()));
      }
    }
    Collections.sort(diceList);
    return diceList;
  }

  private Dice parseDice(String inputStr) { // Just parsing an individual dice roll.
    String diceRoll = inputStr;
    int amount = 1;
    int sides = 6;
    Operator operator = Operator.NONE;
    int operand = 0;

    Matcher m = null;
    if (inputStr.matches(addRollPattern.pattern())) { // Parsing the operand/subtraction portion and then eliminating it.
      m = addRollPattern.matcher(inputStr);
      operator = Operator.ADD;
    } else if (inputStr.matches(subtractRollPattern.pattern())) {
      m = subtractRollPattern.matcher(inputStr);
      operator = Operator.SUBTRACT;
    } else if (inputStr.matches(divideRollPattern.pattern())) {
      m = divideRollPattern.matcher(inputStr);
      operator = Operator.DIVIDE;
    } else if (inputStr.matches(dropLowestRollPattern.pattern())) {
      m = dropLowestRollPattern.matcher(inputStr);
      operator = Operator.DROP_LOWEST;
    } else if (inputStr.matches(dropHighestRollPattern.pattern())) {
      m = dropHighestRollPattern.matcher(inputStr);
      operator = Operator.DROP_HIGHEST;
    } else if (inputStr.matches(keepLowestRollPattern.pattern())) {
      m = keepLowestRollPattern.matcher(inputStr);
      operator = Operator.KEEP_LOWEST;
    } else if (inputStr.matches(keepHighestRollPattern.pattern())) {
      m = keepHighestRollPattern.matcher(inputStr);
      operator = Operator.KEEP_HIGHEST;
    }

    if (m != null && m.find()) {
      diceRoll = m.group(1).trim();
      operand = Integer.parseInt(m.group(4).trim());

      if (operand == 0) {
        operator = Operator.NONE;
      }
    }

    if (diceRoll.matches("\\d+[d|D]\\d+")) { // Parsing the dice roll itself
      String[] parts = diceRoll.split("[d|D]");
      amount = Integer.parseInt(parts[0].trim());
      sides = Integer.parseInt(parts[1].trim());
    } else if (diceRoll.matches("\\d+[d|D]")) { // Don't need to set sides since it's set to 6 by default
      amount = Integer.parseInt(diceRoll.split("[d|D]")[0].trim());
    } else if (diceRoll.matches("[d|D]\\d+")) { // Don't need to set amount since it's set to 1 by default
      sides = Integer.parseInt(diceRoll.split("[d|D]")[1].trim());
    }
    if ((operator == Operator.KEEP_HIGHEST || operator == Operator.DROP_LOWEST || operator == Operator.KEEP_LOWEST || operator == Operator.DROP_HIGHEST) && operand > amount) {
      operand = amount;
    }
    return new Dice(amount, sides, operator, operand);
  }

}

record Dice(int amount, int sides, Operator operator, int operand) implements Comparable<Dice> {

  public Dice {
    if (amount < 1) {
      throw new IllegalArgumentException("field 'amount' must be greater than or equal to 1");
    }
    if (sides < 1) {
      throw new IllegalArgumentException("field 'sides' must be greater than or equal to 1");
    }
    if (amount > 10_000) {
      throw new IllegalArgumentException("field 'amount' must be less than 10,000");
    }
    if (sides > 10_000) {
      throw new IllegalArgumentException("field 'sides' must be less than 10,000");
    }
  }

  private int roll() {
    return new SplittableRandom().nextInt(sides) + 1;
  }

  /**
   * Generates an entirely new list of rolls based on the record input every time the method is called.
   *
   * @return New list of rolls.
   */
  public List<Integer> rolls() {
    List<Integer> rolls = new ArrayList<>();
    for (int i = 0; i < amount; i++) {
      rolls.add(roll());
    }
    return rolls;
  }

  @Override
  public int compareTo(@NotNull Dice o) {
    int n = Integer.compare(this.amount(), o.amount());
    if (n != 0) {
      return n;
    }
    n = Integer.compare(this.sides(), o.sides());
    if (n != 0) {
      return n;
    }
    n = this.operator().compareTo(o.operator());
    if (n != 0) {
      return n;
    }
    return Integer.compare(this.operand(), o.operand());
  }

}

enum Operator {

  NONE,
  ADD,
  SUBTRACT,
  DIVIDE,
  DROP_LOWEST,
  DROP_HIGHEST,
  KEEP_LOWEST,
  KEEP_HIGHEST

}

