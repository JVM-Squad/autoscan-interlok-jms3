/*
 * Copyright 2015 Adaptris Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/

package com.adaptris.core.jms3;

import jakarta.jms.JMSException;
import jakarta.jms.Message;

import com.adaptris.core.AdaptrisMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <p>
 * Default implementation od <code>CorrelationIdSource</code> which does nothing.
 * </p>
 * 
 * @config jms3-null-correlation-id-source
 */
@XStreamAlias("jms3-null-correlation-id-source")
public class NullCorrelationIdSource implements CorrelationIdSource {

  private static final NullCorrelationIdSource NO_CORRELATION = new NullCorrelationIdSource();

  /**
   * @see CorrelationIdSource#processCorrelationId (AdaptrisMessage, Message)
   */
  public void processCorrelationId(AdaptrisMessage src, Message dest)
      throws JMSException {
    // do nothing...
  }

  /**
   * 
   * @see CorrelationIdSource#processCorrelationId(Message, AdaptrisMessage)
   */
  public void processCorrelationId(Message src, AdaptrisMessage dest)
      throws JMSException {
    // nothing to do.
  }

  /**
   * Helper method for null protection.
   * 
   */
  public static CorrelationIdSource defaultIfNull(CorrelationIdSource c) {
    return c != null ? c : NO_CORRELATION;
  }
}
