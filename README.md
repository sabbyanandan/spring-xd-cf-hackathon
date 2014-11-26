spring-xd-cf-hackathon
======================

Spring XD based data-driven dynamic deployment for CF apps.

Idea
----
You have Apps running in CF and you'd like to automatically scale them on-demand. A demand can be throughput based or may be derived from metrics or perhaps you would want to adjust sclaing based on a certain day/time (ex: Thanksgiving, Christmas, ..)

Spring XD fits perfectly for this requirement. The [source](https://github.com/spring-projects/spring-xd/wiki/Sources) and [sink](https://github.com/spring-projects/spring-xd/wiki/Sinks) modules could expedite operationalizing data-driven dynamic deployments seamlessly. You can get this up and running out-of-the-box and the important fact is that you don't write any code.

Following are few approaches to this.

* Measure the throughput based on CPU/Data transfer rates via logs or events generated by Apps for a specific time-window. Let those events get consumed by Spring XD through 'HTTP' source module.
* Measure the API activities by routing the events to a pub/sub broker like 'Kafka' or 'Rabbit' and have Spring XD consume from them directly.
* Trigger the scaling protocol based on a timed-event through 'Trigger' source module.

Sample Streams
--------------

|Stream Name | Stream Definition
|--------------|--------
HTTP Stream | `stream create httpstream --definition "http | transform --script=postRequest.groovy | log" --deploy`
Rabbit Stream | `stream create --name rabbitstream --definition "rabbit --queues='scale-out' | transform --script=postRequest.groovy | counter" --deploy`
Trigger Stream | `stream create --name triggerstream --definition "trigger --date='11/25/14 17:22:00' | transform --script=postRequest.groovy | counter" --deploy1`