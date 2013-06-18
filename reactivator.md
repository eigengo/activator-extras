#Reactivator
Execute the SBT projects in _preflight_ mode, where we collect the dependencies. Then analyse [implicit.ly](implicit.ly) and other sources for artefact update information. We will need to find a way to describe artefact upgrade paths.

Then, when executed on a previously _activated_ project, build the upgrade plan. Point out the breaking changes, potential traps and any other hints we have. 

Here's the interesting bit: If possible, have some sort of DSL to describe the suggested upgrade paths. Something that can deal with the syntax of Scala, but that does not need the full compiler support. Something along the lines of

```scala
import akka.actor.{ ActorSystem, Props }
(:ActorSystem).actorOf(Props(new 'a)) ==> (:ActorSystem).actorOf(Props['a])
```

As human, I'd read this as: ``Props(new X)`` used as parameter to the ``actorOf`` method of the ``ActorSystem`` should become ``Props[X]``; ``'a`` here is some type with nullary constructor; ``(:ActorSystem)`` simply means expression of type ``ActorSystem``. Let's not get too hung up on the syntax, I bet that'll change over time.

So, I propose to have computer-aided upgrade. Authors of various libraries and frameworks could then use this tool to help their users in migrating their code to newer versions.

Before I start messing around with the code, do you think that your project is a good fit for it? Also, have I gone completely mad or is this something you think could work & be useful?

I'd be grateful for your comments.
