Prerequisites:

Apache ActiveMQ must be installed.
The ApacheMQ broker should be running on its default port of 61616

To Run:

Run JmsSenderApp or JmsListenerApp from within Intellij.

Run the sender first to push some messages onto the topic/queue.
Messages may be lost from topic is listener is not run first - this is because the listener must
register itself as a durable subscriber with the broker