# RequestManager
Aplikacja ma za zadanie zmianę stanu wniosku w zależności od przechodzenia pomiędzy kolejnymi etapami weryfikacji. Projekt został napisany w środowisku Java (jdk1.7.0_51) i Netbeans 8.0.2. Projekt. Narzędziem wspomagającym jest Apache Maven.

W tym celu zostało utworzne 4 pakiety:

com.requestmanager.main - main class com.requestmanager.controller - business logic com.requestmanager.model - model com.requestmanager.utils - helper

RequestManager.java - inicjalizacja aplikacji. Utworzono 5 scenariuszy, które tworzą podstawowy flow zmiany stanów wniosków. RequestHandler.java - klasa w której zostały zaimplementowane wszystkie metody do obsługi zmianów stanu w aplikacji.

showRequests(itemsPerPage) - wyświetla wszystkie wnioski z odpowienią ilością na stronie

showFilteredRequests(String requestName, EnumStatus.Status status, int itemsPerPage) - wyświtla przefiltrowanie wnioski z odpowiednią ilością na stronie

createNewRequest(String requestName, String requestDescription) - utworzenie nowego wniosku

createNewVersion(Request r, EnumStatus.Status status) - tworzy historię zmiany stanu danego wniosku

deleteRequest(String toBeDeleted, String requestReasonRejected, EnumStatus.Status s) - zmienia stan wniosku na Deleted

verifyRequest(String toBeVerified, String requestDescription, EnumStatus.Status s) - zmienia stan wniosku na verified

rejectRequest(String toBeRejected, String requestReasonRejected, EnumStatus.Status s) - zmienia stan wniosku na rejected

acceptRequest(String toBeAccepted, EnumStatus.Status s) - zmienia stan wniosku na accepted

publishRequest(String toBePublished, EnumStatus.Status s) - zmienia stan wniosku na published

Wszystkie zmiany są zapisywane w pliku requests.xml w głownym katalogu aplikacji. Struktura drzewka umożliwia utworzenie relacji pomiędzy historią a wnioskiem. W celu wczytywania i zapisywania powyższej struktury użyto bibliotek JAXB.

Model danych zawiera następujące definicje: Version.java - obiekt przechowujący historię zmian wniosku Requests.java - obiekt przechowujący listę wniosków Request.java - obiekt przechowujący wniosek EnumStatus.java - możliwe stany wniosku

W pakiecie Utils znajdują się funkcje pomocnicze.
