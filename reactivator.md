#Typesafe Reactivator
Execute the SBT projects in _preflight_ mode, where we collect the dependencies. Then analyse [implicit.ly](implicit.ly) and other sources for artefact update information. We will need to find a way to describe artefact upgrade paths.

Then, when executed on a previously _activated_ project, build the upgrade plan. Point out the breaking changes, potential traps and any other hints we have.

Build the system. Host it. Extend Activator to take advantage of it.