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
package com.graphqlio.gtt.types;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;

/**
 * Class used to implement JSON for graphql scalar types represented by Strings correct JSON-format
 * is checked
 *
 * @author Michael Schäfer
 * @author Torsten Kühnert
 * @author Wei Scheng
 */
public class GttJsonType extends GraphQLScalarType {

  private static final String DEFAULT_NAME = "JSON";

  public GttJsonType() {
    this(DEFAULT_NAME);
  }

  public GttJsonType(final String name) {
    super(
        name,
        DEFAULT_NAME + " type",
        new Coercing<String, String>() {

          @Override
          public String parseLiteral(Object arg0) throws CoercingParseLiteralException {
            if (arg0 instanceof StringValue) {
              try {
                String value = ((StringValue) arg0).getValue();
                return testAndConvertJson2String(value);
              } catch (Exception e) {
                throw new CoercingParseLiteralException(e);
              }
            } else {
              throw new CoercingParseLiteralException(
                  "parseLiteral: Expected a 'StringValue' but was '" + (arg0.getClass()) + "'.");
            }
          }

          @Override
          public String parseValue(Object arg0) throws CoercingParseValueException {
            if (arg0 instanceof String) {
              try {
                String value = (String) arg0;
                return testAndConvertJson2String(value);
              } catch (Exception e) {
                throw new CoercingParseValueException(e);
              }
            } else {
              throw new CoercingParseValueException(
                  "parseValue: Expected a 'String' but was '" + (arg0.getClass()) + "'.");
            }
          }

          @Override
          public String serialize(Object arg0) throws CoercingSerializeException {
            if (arg0 instanceof String) {
              try {
                String value = (String) arg0;
                return testAndConvertJson2String(value);
              } catch (Exception e) {
                throw new CoercingSerializeException(e);
              }
            } else {
              throw new CoercingSerializeException(
                  "serialize: Expected a 'String' but was '" + (arg0.getClass()) + "'.");
            }
          }

          private String testAndConvertJson2String(String value) throws Exception {
            try {
              // 1. try JSONObject
              JSONObject obj = new JSONObject(value);
              return obj.toString();

            } catch (Exception e1) {
              String eMsg = e1.getMessage();

              try {
                // 2. try JSONArray
                JSONArray obj = new JSONArray(value);
                return obj.toString();

              } catch (Exception e2) {
                eMsg += " and " + e2.getMessage();
                throw new Exception(eMsg);
              }
            }
          }
        });
  }
}
