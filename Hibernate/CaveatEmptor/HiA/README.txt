Hibernate Application: CaveatEmptor (HiA)
=====================================================
version 0.9.6, xx. xxx 2004


Purpose
-----------------------------------------------------

CaveatEmptor is an online auction system, it has
entities such as item, user, and bid. This Hibernate
demo application was originally written for the book 
"Hibernate in Action", published by Manning Inc.

This software is distributed under the terms of the FSF
Lesser Gnu Public License (see LGPL.txt).

CaveatEmptor is distributed in several packages, each
having a different focus. This is the "HiA" version,
which only implements the persistence layer of the book
"Hibernate in Action".


Install & Run
-----------------------------------------------------

This particular version (HiA) is not fully runable, it is
only the implementation of the persistence layer of
the CaveatEmptor example application used in "Hibernate
in Action".

Use this source code as a reference when learning Hibernate
with "Hibernate in Action". You can copy (or re-implement if
the LGPL is not your license) parts of the persistence layer
infrastructure, such as HibernateUtil, HibernateFilter, etc.
for your own project.

You can check the functionality of the persistence layer
by using the provided JUnit tests, run "ant junitreport" for
a test using the bundled HSQL database. Change the database
settings in src/java/hibernate.cfg.xml if you'd like to use
different settings. After the test runs successfully, browse
the test result in the testout/ directory.

The database schema will be automatically imported and dropped
from your database for each unit test. Run "ant exportddl"
if you want to look at the schema, it creates caveatemptor.ddl.

Note: Ant 1.6.x is required, but unfortunately ships with all
optional tasks in $ANT_HOME/lib. This leads to unexpected
classloader problems. Copy lib/junit.jar to $ANT_HOME/lib to
solve this problem temporarily. This might only work on some
systems, on others, remove ant-junit* and ant-xalan* from
$ANT_HOME/lib. See the Ant documentation and FAQ for more
details and don't hesitate to ask the Ant developers.

Note: The unit tests will not work on a MySQL database without
support for SQL subselects!

Contact & Forum
-----------------------------------------------------

http://caveatemptor.hibernate.org/
http://forum.hibernate.org/viewforum.php?f=7
