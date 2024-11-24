package org.example.homework01.validParentheses;

import java.util.HashMap;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        System.out.println(isValid("()"));
        System.out.println(isValid("([])"));
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("(]"));
        System.out.println(isValid("{(]})"));
    }

    public static boolean isValid(String str) {

        HashMap<Character, Character> bracketsMap = new HashMap<>();
        bracketsMap.put('{', '}');
        bracketsMap.put('[', ']');
        bracketsMap.put('(', ')');

        Stack<Character> closingBrackets = new Stack<>();

        Character curr;
        for(int i = 0; i < str.length(); i++) {
            curr = str.charAt(i);

            if(bracketsMap.containsValue(curr) && closingBrackets.search(curr) == -1) {
                return false;
            }

            if(bracketsMap.containsKey(curr)) {
                closingBrackets.push(bracketsMap.get(curr));
                continue;
            }

            if(closingBrackets.empty() || closingBrackets.search(curr) == -1) {
                continue;
            }

            closingBrackets.remove(curr);
        }

        return closingBrackets.empty();
    }
}