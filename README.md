#JDBC # CRUD #MySQL #Servlet #JSP #Maven #Eclipse

Cruise-Log - version JDBC

Pierwsza ods³ona loggera rejsów ¿eglarskich. Prosta aplikacja pozwalaj¹ca na zapisywanie informacji na temat odbytych rejsów. Aplikacja umo¿liwia wykonywanie operacji CRUD(zapisu/odczytu/aktualizacji/usuwania) na bazie danych (H2 lub MySQL) za pomoc¹ podstawowej biblioteki JDBC. Wybór bazy danych determinowany jest w klasie CruiseDaoImpl (wiersze 22/23). W za³¹czonych plikach MySql/H2 Script znajduj¹ siê skrypty konieczne do utworzenia tabeli w bazie danych. Warstwa widoku wykorzystuje strony JSP z tagami JSTL. Do testowania aplikacji przewidziano generator losowych rejsów umo¿liwiaj¹cy utworzenie przyk³adowych danych.

////

First version of sailing cruises logger. Simple app that allows you to save information about your cruises. Application provide CRUD operations (Create/Read/Update/Delete) on database (H2 or MySQL) using the basic JDBC library. DB selection is determined in CruiseDAOImpl class (lines 22/23). View layer uses JSP pages with JSTL tags. For testing purposes there is random-cruise service that can create sample data.