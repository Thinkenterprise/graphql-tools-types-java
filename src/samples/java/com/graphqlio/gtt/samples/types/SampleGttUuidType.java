/**
 * *****************************************************************************
 *
 * <p>Design and Development by msg Applied Technology Research Copyright (c) 2019-2020 msg systems
 * ag (http://www.msg-systems.com/) All Rights Reserved.
 *
 * <p>Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * <p>The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * <p>THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * <p>****************************************************************************
 */
package com.graphqlio.gtt.samples.types;

import com.graphqlio.gtt.types.GttUuidType;

import graphql.language.StringValue;

/**
 * Class showing use of Uuid scalar type
 *
 * @author Michael Schäfer
 * @author Torsten Kühnert
 */
public class SampleGttUuidType {

  public static void main(String[] args) {
    new SampleGttUuidType().run();
  }

  public void run() {
    GttUuidType type = new GttUuidType();

    /*
     * some scalar type information
     */
    System.out.println("type.getDescription = " + type.getDescription());
    System.out.println("type.getName = " + type.getName());
    System.out.println("type = " + type);

    /*
     * literal parsing
     */
    Object result =
        type.getCoercing().parseLiteral(new StringValue("3b241101-e2bb-4255-8caf-4136c566a964"));
    System.out.println("result.class = " + result.getClass().getName());
    System.out.println("result = " + result);

    /*
     * value parsing
     */
    result = type.getCoercing().parseValue("3b241101-e2bb-4255-8caf-4136c566a964");
    System.out.println("result.class = " + result.getClass().getName());
    System.out.println("result = " + result);

    /*
     * serialization
     */
    result = type.getCoercing().serialize(result);
    System.out.println("result.class = " + result.getClass().getName());
    System.out.println("result = " + result);
  }
}
