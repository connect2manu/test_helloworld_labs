Hibernate Application: CaveatEmptor (HiA Second Edition)
=========================================================
version 3.1 alpha 5, 23.11.2005

Note: The second edition of Hibernate in Action has not 
been released yet, this code is a preview for interested
users and readers of the first edition.

Note: This preview release might contain unfinished code or
libraries that are not needed in the final release. Use your
own judgement and the README files of Hibernate, Hibernate
Annotations, and Hibernate EntityManager.


Purpose
-----------------------------------------------------

CaveatEmptor is an online auction system, it has
entities such as item, user, and bid. This Hibernate
demo application was originally written for the book 
"Hibernate in Action", published by Manning Inc.

CaveatEmptor is distributed in several packages, each
having a different focus. This is the "HiA" version,
which only implements the persistence layer of the book
"Hibernate in Action", Second Edition. It also includes
EJB 3.0 mapping and API examples.


Install
-----------------------------------------------------

Use this source code as a reference when learning Hibernate
with "Hibernate in Action". You can copy (or re-implement if
the LGPL is not your license) parts of the persistence layer
infrastructure, such as HibernateUtil, HibernateFilter, etc.
for your own project.

Bundled are three different test targets and examples:

- JUnit tests of the Hibernate persistence layer
- TestNG tests for everything, using JBoss EJB 3.0 microcontainer
- Webapp tests for various filters and listeners

All runable targets use an HSQL DB server on localhost.
You have to start the database with the runCleanDatabase.sh
script. You have to restart the database if you switch between
different test targets, and annotation or XML mappings.

To switch to a different database, edit

- src/etc/junit/hibernate.cfg.xml
- src/etc/testng/META-INF/persistence.xml
- src/etc/webapp.test/conf/hibernate.cfg.xml

The database schema will be automatically imported and dropped
from your database for each unit test run. Use "ant db.ddl2file"
if you want to see the the schema exported to build/caveatemptor.ddl.

Note: Ant 1.6.x is required, but unfortunately ships with all
optional tasks in $ANT_HOME/lib. This leads to unexpected
classloader problems. Copy lib/junit.jar to $ANT_HOME/lib to
solve this problem temporarily. This might only work on some
systems, on others, remove ant-junit* and ant-xalan* from
$ANT_HOME/lib. See the Ant documentation and FAQ for more
details and don't hesitate to ask the Ant developers why they
decided to bundle plugin stubs in the Ant distribution.

Note: The unit tests will not work on a MySQL database without
support for SQL subselects!


Run JUnit tests
-----------------------------------------------------

You can check the functionality of the persistence layer
by using the provided JUnit tests, run "ant junit.run"
for all tests using the bundled HSQL database.

After the test runs successfully, browse the test result in the 
build/junit/testout/ directory and have a look at the source code.

Both mapping variations are included, Hibernate native XML files
and EJB3/H3 annotations in the POJO source code. You can switch
between the two by removing and commenting the mapping resources blocks
in src/etc/junit/hibernate.cfg.xml.


Run TestNG tests
-----------------------------------------------------

Use "ant testng.run" to test the EJB 3.0 code with the
embeddable JBoss EJB 3.0 microcontainer. This test always
uses the annotated entity beans, not the XML mapping files.


Run web application tests
-----------------------------------------------------

Included are useful listeners and filters for Hibernate web
applications. Edit build.properties and change $tomcat.home to your
Tomcat installation, then run "ant webapp.test.deploy" and
access the tests by pointing your browser to
http://localhost:8080/caveatemptor-test/


Explore the database
-----------------------------------------------------

To browse the running database, use the built-in SQL frontend of
HSQL DB with "ant db.gui". Edit build.properties to change
connection settings for your database. Run "ant junit.run.importdata"
to only import test data without running tests or dropping the
data after the test.


Contact & Forum
-----------------------------------------------------

http://caveatemptor.hibernate.org/
http://forum.hibernate.org/viewforum.php?f=7


License
-----------------------------------------------------

This product includes software developed by
the Apache Software Foundation (http://www.apache.org/).

This software is distributed as open source.
The following disclaimer applies to all files
(not any included 3rd party libraries) distributed in
this package:

Copyright (c) 2005, Christian Bauer <christian@hibernate.org>
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

- Redistributions of source code must retain the above copyright notice, this
list of conditions and the following disclaimer.

- Redistributions in binary form must reproduce the above copyright notice,
this list of conditions and the following disclaimer in the documentation
and/or other materials provided with the distribution.

Neither the name of the original author nor the names of contributors may be
used to endorse or promote products derived from this software without
specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
THE POSSIBILITY OF SUCH DAMAGE.
