package com.simple.admin.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

public class AntPathMatcher
{
  public static final String DEFAULT_PATH_SEPARATOR = "/";
  private String pathSeparator = "/";

  public void setPathSeparator(String pathSeparator) {
    this.pathSeparator = (pathSeparator != null ? pathSeparator : "/");
  }

  public boolean match(String pattern, String path)
  {
    return doMatch(pattern, path, true);
  }

  protected boolean doMatch(String pattern, String path, boolean fullMatch)
  {
    if (path.startsWith(this.pathSeparator) != pattern.startsWith(this.pathSeparator)) {
      return false;
    }

    String[] pattDirs = tokenizeToStringArray(pattern, this.pathSeparator);
    String[] pathDirs = tokenizeToStringArray(path, this.pathSeparator);

    int pattIdxStart = 0;
    int pattIdxEnd = pattDirs.length - 1;
    int pathIdxStart = 0;
    int pathIdxEnd = pathDirs.length - 1;

    while ((pattIdxStart <= pattIdxEnd) && (pathIdxStart <= pathIdxEnd)) {
      String patDir = pattDirs[pattIdxStart];
      if ("**".equals(patDir)) {
        break;
      }
      if (!matchStrings(patDir, pathDirs[pathIdxStart])) {
        return false;
      }
      pattIdxStart++;
      pathIdxStart++;
    }

    if (pathIdxStart > pathIdxEnd) {
      if (pattIdxStart > pattIdxEnd) {
        return !path.endsWith(this.pathSeparator);
      }

      if (!fullMatch) {
        return true;
      }
      if ((pattIdxStart == pattIdxEnd) && (pattDirs[pattIdxStart].equals("*")) && (path.endsWith(this.pathSeparator)))
      {
        return true;
      }
      for (int i = pattIdxStart; i <= pattIdxEnd; i++) {
        if (!pattDirs[i].equals("**")) {
          return false;
        }
      }
      return true;
    }
    if (pattIdxStart > pattIdxEnd) {
      return false;
    }
    if ((!fullMatch) && ("**".equals(pattDirs[pattIdxStart]))) {
      return true;
    }

    while ((pattIdxStart <= pattIdxEnd) && (pathIdxStart <= pathIdxEnd)) {
      String patDir = pattDirs[pattIdxEnd];
      if (patDir.equals("**")) {
        break;
      }
      if (!matchStrings(patDir, pathDirs[pathIdxEnd])) {
        return false;
      }
      pattIdxEnd--;
      pathIdxEnd--;
    }
    if (pathIdxStart > pathIdxEnd) {
      for (int i = pattIdxStart; i <= pattIdxEnd; i++) {
        if (!pattDirs[i].equals("**")) {
          return false;
        }
      }
      return true;
    }

    while ((pattIdxStart != pattIdxEnd) && (pathIdxStart <= pathIdxEnd)) {
      int patIdxTmp = -1;
      for (int i = pattIdxStart + 1; i <= pattIdxEnd; i++) {
        if (pattDirs[i].equals("**")) {
          patIdxTmp = i;
          break;
        }
      }
      if (patIdxTmp == pattIdxStart + 1) {
        pattIdxStart++;
      }
      else {
        int patLength = patIdxTmp - pattIdxStart - 1;
        int strLength = pathIdxEnd - pathIdxStart + 1;
        int foundIdx = -1;

        int i = 0; if (i <= strLength - patLength) {
          for (int j = 0; j < patLength; j++) {
            String subPat = pattDirs[(pattIdxStart + j + 1)];
            String subStr = pathDirs[(pathIdxStart + i + j)];
            if (!matchStrings(subPat, subStr)) {
              break;
            }
          }
          foundIdx = pathIdxStart + i;
        }

        label541: if (foundIdx == -1) {
          return false;
        }

        pattIdxStart = patIdxTmp;
        pathIdxStart = foundIdx + patLength;
      }
    }
    for (int i = pattIdxStart; i <= pattIdxEnd; i++) {
      if (!pattDirs[i].equals("**")) {
        return false;
      }
    }

    return true;
  }

  private boolean matchStrings(String pattern, String str) {
    char[] patArr = pattern.toCharArray();
    char[] strArr = str.toCharArray();
    int patIdxStart = 0;
    int patIdxEnd = patArr.length - 1;
    int strIdxStart = 0;
    int strIdxEnd = strArr.length - 1;

    boolean containsStar = false;
    for (char aPatArr : patArr) {
      if (aPatArr == '*') {
        containsStar = true;
        break;
      }
    }

    if (!containsStar) {
      if (patIdxEnd != strIdxEnd) {
        return false;
      }
      for (int i = 0; i <= patIdxEnd; i++) {
        char ch = patArr[i];
        if ((ch != '?') && (ch != strArr[i])) {
          return false;
        }
      }

      return true;
    }

    if (patIdxEnd == 0)
      return true;
    char ch;
    while (((ch = patArr[patIdxStart]) != '*') && (strIdxStart <= strIdxEnd)) {
      if ((ch != '?') && (ch != strArr[strIdxStart])) {
        return false;
      }

      patIdxStart++;
      strIdxStart++;
    }
    if (strIdxStart > strIdxEnd)
    {
      for (int i = patIdxStart; i <= patIdxEnd; i++) {
        if (patArr[i] != '*') {
          return false;
        }
      }
      return true;
    }

    while (((ch = patArr[patIdxEnd]) != '*') && (strIdxStart <= strIdxEnd)) {
      if ((ch != '?') && (ch != strArr[strIdxEnd])) {
        return false;
      }

      patIdxEnd--;
      strIdxEnd--;
    }
    if (strIdxStart > strIdxEnd)
    {
      for (int i = patIdxStart; i <= patIdxEnd; i++) {
        if (patArr[i] != '*') {
          return false;
        }
      }
      return true;
    }

    while ((patIdxStart != patIdxEnd) && (strIdxStart <= strIdxEnd)) {
      int patIdxTmp = -1;
      for (int i = patIdxStart + 1; i <= patIdxEnd; i++) {
        if (patArr[i] == '*') {
          patIdxTmp = i;
          break;
        }
      }
      if (patIdxTmp == patIdxStart + 1) {
        patIdxStart++;
      }
      else {
        int patLength = patIdxTmp - patIdxStart - 1;
        int strLength = strIdxEnd - strIdxStart + 1;
        int foundIdx = -1;
        int i = 0; if (i <= strLength - patLength) {
          for (int j = 0; j < patLength; j++) {
            ch = patArr[(patIdxStart + j + 1)];
            if ((ch != '?') && (ch != strArr[(strIdxStart + i + j)]))
            {
              break;
            }
          }
          foundIdx = strIdxStart + i;
        }

        label470: if (foundIdx == -1) {
          return false;
        }

        patIdxStart = patIdxTmp;
        strIdxStart = foundIdx + patLength;
      }
    }

    for (int i = patIdxStart; i <= patIdxEnd; i++) {
      if (patArr[i] != '*') {
        return false;
      }
    }

    return true;
  }

  public static String[] tokenizeToStringArray(String str, String delimiters) {
    return tokenizeToStringArray(str, delimiters, true, true);
  }

  public static String[] tokenizeToStringArray(String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens)
  {
    if (str == null) {
      return null;
    }
    StringTokenizer st = new StringTokenizer(str, delimiters);
    List tokens = new ArrayList();
    while (st.hasMoreTokens()) {
      String token = st.nextToken();
      if (trimTokens) {
        token = token.trim();
      }
      if ((!ignoreEmptyTokens) || (token.length() > 0)) {
        tokens.add(token);
      }
    }
    return toStringArray(tokens);
  }

  public static String[] toStringArray(Collection collection) {
    if (collection == null) {
      return null;
    }
    return (String[])collection.toArray(new String[collection.size()]);
  }
}