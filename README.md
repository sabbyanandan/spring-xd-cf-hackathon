Data-driven Dynamic Deployment using Spring XD
==============================================

[Spring XD](https://github.com/spring-projects/spring-xd) is a unified, distributed, and extensible service for data ingestion, real time analytics, batch processing, and data export. The Spring XD project is an open source Apache 2 License licenced project whose goal is to tackle big data complexity.

Idea
----
You have Apps running in [Cloud Foundry](http://www.pivotal.io/platform-as-a-service/pivotal-cf) and you'd like to automatically scale them on-demand. The demand can be throughput based or may be derived from 'some' metrics or perhaps you would want to adjust scaling based on a certain day/time. _(example: Thanksgiving, Christmas, ..)_

Spring XD fits perfectly for this requirement. The [source](https://github.com/spring-projects/spring-xd/wiki/Sources) and [sink](https://github.com/spring-projects/spring-xd/wiki/Sinks) modules could expedite operationalizing data-driven dynamic deployments seamlessly. You can get this up and running out-of-the-box and the important fact is that you don't write any code.

Following are few approaches:

* Measure the throughput based on CPU/Data transfer rates via logs or events generated by Apps for a specific time-window. Let those events get consumed by Spring XD through 'HTTP' source module.
* Measure the API activities by routing the events to a pub/sub broker like 'Kafka' or 'Rabbit' and have Spring XD consume from them directly.
* Trigger the scaling protocol based on a timed-event through 'Trigger' source module. _(example: trigger the auto-scale on 11/27/2014 at midnight)_

Spring XD -> Streams
--------------------

|Stream Name | Stream Definition
|--------------|--------
HTTP Stream | `stream create httpstream --definition "http | transform --script=postRequest.groovy | log" --deploy`
Rabbit Stream | `stream create --name rabbitstream --definition "rabbit --queues='scale-out' | transform --script=postRequest.groovy | counter" --deploy`
Trigger Stream | `stream create --name triggerstream --definition "trigger --date='11/27/14 00:00:00' | transform --script=postRequest.groovy | counter" --deploy`

_(Assumption is that you have Spring XD [running](https://github.com/spring-projects/spring-xd/wiki/Getting-Started) either in singlenode or distributed mode. You can then deploy the above streams.)_