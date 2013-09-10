Hibernate Application: CaveatEmptor (sqlbench)
=====================================================
version 0.x.x, xx. xxxx 2004


Purpose
-----------------------------------------------------

CaveatEmptor is an online auction system, it has
entities such as item, user, and bid. This Hibernate
demo application was originally written for the book 
"Hibernate in Action", published by Manning Inc.

This software is distributed under the terms of the FSF
Lesser Gnu Public License (see LGPL.txt).

CaveatEmptor is distributed in several packages, each
having a different focus.

This is the "sqlbench" version.


Install & Run
-----------------------------------------------------

Run "ant" to run the SQL benchmark. To see all executed
SQL statements, set

log4j.logger.org.hibernate.auction.bench=debug

in /etc/log4j.properties.

Note: Ant 1.6.x is required, but unfortunately ships with all
optional tasks in $ANT_HOME/lib. This leads to unexpected
classloader problems. Copy lib/junit.jar to $ANT_HOME/lib to
solve this problem temporarily. This might only work on some
systems, on others, remove ant-junit* and ant-xalan* from
$ANT_HOME/lib. See the Ant documentation and FAQ for more
details and don't hesitate to ask the Ant developers.


Contact & Forum
-----------------------------------------------------

http://caveatemptor.hibernate.org/
http://forum.hibernate.org/viewforum.php?f=7
