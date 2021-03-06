/*
Copyright (c) 2005, Pete Bevin.
<http://markdownj.petebevin.com>

All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are
met:

* Redistributions of source code must retain the above copyright notice,
  this list of conditions and the following disclaimer.

* Redistributions in binary form must reproduce the above copyright
  notice, this list of conditions and the following disclaimer in the
  documentation and/or other materials provided with the distribution.

* Neither the name "Markdown" nor the names of its contributors may
  be used to endorse or promote products derived from this software
  without specific prior written permission.

This software is provided by the copyright holders and contributors "as
is" and any express or implied warranties, including, but not limited
to, the implied warranties of merchantability and fitness for a
particular purpose are disclaimed. In no event shall the copyright owner
or contributors be liable for any direct, indirect, incidental, special,
exemplary, or consequential damages (including, but not limited to,
procurement of substitute goods or services; loss of use, data, or
profits; or business interruption) however caused and on any theory of
liability, whether in contract, strict liability, or tort (including
negligence or otherwise) arising in any way out of the use of this
software, even if advised of the possibility of such damage.

*/

package com.xinball.carbonforum.util.markdown;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLDecoder {
    public static String decode(String html) {
        com.xinball.carbonforum.util.markdown.TextEditor ed = new com.xinball.carbonforum.util.markdown.TextEditor(html);
        Pattern p1 = Pattern.compile("&#(\\d+);");
        ed.replaceAll(p1, new com.xinball.carbonforum.util.markdown.Replacement() {
            public String replacement(Matcher m) {
                String charDecimal = m.group(1);
                char ch = (char) Integer.parseInt(charDecimal);
                return Character.toString(ch);
            }
        });

        Pattern p2 = Pattern.compile("&#x([0-9a-fA-F]+);");
        ed.replaceAll(p2, new com.xinball.carbonforum.util.markdown.Replacement() {
            public String replacement(Matcher m) {
                String charHex = m.group(1);
                char ch = (char) Integer.parseInt(charHex, 16);
                return Character.toString(ch);
            }
        });

        return ed.toString();
    }
}
