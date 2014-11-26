spring-xd-cf-hackathon
======================

Spring XD based data-driven dynamic deployments of CF apps.

Idea
----

Usage
-----

|Type | Elements
|------|--------
HTTP Stream | stream create httpstream --definition "http | transform --script=postRequest.groovy | log" --deploy
Trigger Stream | stream create --name triggerstream --definition "trigger --date='11/25/14 17:22:00' | transform --script=postRequest.groovy | counter" --deploy
Rabbit Stream | stream create --name rabbitstream --definition "rabbit --queues='scale-out' | transform --script=postRequest.groovy | counter" --deploy