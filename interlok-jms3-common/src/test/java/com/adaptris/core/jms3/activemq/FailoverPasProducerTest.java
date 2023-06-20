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

package com.adaptris.core.jms3.activemq;

import static com.adaptris.core.jms3.activemq.EmbeddedArtemis.createMessage;
import static com.adaptris.interlok.junit.scaffolding.BaseCase.execute;
import static com.adaptris.interlok.junit.scaffolding.jms.JmsProducerCase.assertMessages;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import com.adaptris.core.StandaloneConsumer;
import com.adaptris.core.StandaloneProducer;
import com.adaptris.core.jms3.PasConsumer;
import com.adaptris.core.jms3.PasProducer;
import com.adaptris.core.stubs.MockMessageListener;

public class FailoverPasProducerTest {

  private static EmbeddedArtemis activeMqBroker;

  @BeforeAll
  public static void setUpAll() throws Exception {
    activeMqBroker = new EmbeddedArtemis();
    activeMqBroker.start();
  }
  
  @AfterAll
  public static void tearDownAll() throws Exception {
    if(activeMqBroker != null)
      activeMqBroker.destroy();
  }

  @Test
  public void testProduceAndConsume(TestInfo info) throws Exception {
    StandaloneConsumer standaloneConsumer =
        new StandaloneConsumer(activeMqBroker.getFailoverJmsConnection(false),
            new PasConsumer().withTopic(info.getDisplayName()));
    MockMessageListener jms = new MockMessageListener();
    standaloneConsumer.registerAdaptrisMessageListener(jms);
    StandaloneProducer standaloneProducer =
        new StandaloneProducer(activeMqBroker.getFailoverJmsConnection(false),
            new PasProducer().withTopic(info.getDisplayName()));
    execute(standaloneConsumer, standaloneProducer, createMessage(null), jms);
    assertMessages(jms, 1);
  }


}
