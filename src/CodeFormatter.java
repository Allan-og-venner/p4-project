public class CodeFormatter {
    private static boolean peekNextNonWhitespaceCharIs(String source, int startPos, char expected) {
        int pos = startPos;
        while (pos < source.length() && Character.isWhitespace(source.charAt(pos))) {
            pos++;
        }
        return pos < source.length() && source.charAt(pos) == expected;
    }
    /**
     * FormatCode is a method that takes unformatted code as input and returns formatted code.
     * It is used to ensure correct and uniform formatting
     * @param unformattedCode String of text to be formatted correctly
     * @return formattedCode
     */
    public static String formatCode(String unformattedCode) {

        unformattedCode = unformattedCode.replaceAll("\n","");

        StringBuilder formattedCode = new StringBuilder();
        int indentation = 0;
        boolean isNewLineNeeded = false;
        boolean inForLoopDeclaration = false;
        int parenthesesDepth = 0;

        char[] codeChars = unformattedCode.toCharArray();
        for (int i = 0; i < codeChars.length; i++) {
            char c = codeChars[i];

            if (!inForLoopDeclaration && i + 3 < codeChars.length && new String(codeChars, i, 3).equals("for")) {
                if (!Character.isJavaIdentifierPart(codeChars[i - 1])) {
                    inForLoopDeclaration = true;
                }
            }

            if (isNewLineNeeded && !inForLoopDeclaration && c != '}' && c != ';' && !Character.isWhitespace(c)) {
                formattedCode.append('\n');
                formattedCode.append("\t".repeat(Math.max(0, indentation)));
                isNewLineNeeded = false;
            }

            switch (c) {
                case '{':
                    formattedCode.append(c).append('\n');
                    indentation++;
                    formattedCode.append("\t".repeat(Math.max(0, indentation)));
                    break;
                case '}':
                    indentation--;
                    formattedCode.append('\n');
                    formattedCode.append("\t".repeat(Math.max(0, indentation)));
                    formattedCode.append(c);
                    if (!peekNextNonWhitespaceCharIs(unformattedCode, i + 1, '}')) {
                        isNewLineNeeded = true;
                    }
                    if(indentation == 0) {
                        formattedCode.append('\n');
                    }
                    break;
                case ';':
                    formattedCode.append(c);
                    if (!inForLoopDeclaration) {
                        isNewLineNeeded = true;
                    }
                    break;
                case '(':
                    if (inForLoopDeclaration) {
                        parenthesesDepth++;
                    }
                    formattedCode.append(c);
                    break;
                case ')':
                    if (inForLoopDeclaration) {
                        parenthesesDepth--;
                        if (parenthesesDepth == 0) {
                            inForLoopDeclaration = false;
                        }
                    }
                    formattedCode.append(c);
                    break;
                default:
                    formattedCode.append(c);
                    break;
            }
        }
        return formattedCode.toString().replaceAll("@Override", "@Override\n");
    }

}
