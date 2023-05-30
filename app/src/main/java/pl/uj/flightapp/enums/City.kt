package pl.uj.flightapp.enums

enum class City(val cityName: String, val iataCode: String) {
    AMSTERDAM("Amsterdam", "AMS"),
    ATHENS("Ateny", "ATH"),
    BAKU("Baku", "GYD"),
    BELGRADE("Belgrad", "BEG"),
    BELFAST("Belfast", "BFS"),
    BERN("Berno", "BRN"),
    BRATISLAVA("Bratysława", "BTS"),
    BRUSSELS("Bruksela", "BRU"),
    BUCHAREST("Bukareszt", "OTP"),
    BUDAPEST("Budapeszt", "BUD"),
    DUBLIN("Dublin", "DUB"),
    EDINBURGH("Edynburg", "EDI"),
    HELSINKI("Helsinki", "HEL"),
    KIEV("Kijów", "KBP"),
    LISBON("Lizbona", "LIS"),
    LONDON("Londyn", "LHR"),
    LUXEMBOURG("Luksemburg", "LUX"),
    LJUBLJANA("Lublana", "LJU"),
    MADRID("Madryt", "MAD"),
    VALLETTA("Valletta", "MLA"),
    MINSK("Mińsk", "MSQ"),
    MONACO("Monako", "MCM"),
    MOSCOW("Moskwa", "SVO"),
    OSLO("Oslo", "OSL"),
    PARIS("Paryż", "CDG"),
    PRAGUE("Praga", "PRG"),
    REYKJAVIK("Reykjavik", "KEF"),
    ROME("Rzym", "FCO"),
    SARAJEVO("Sarajewo", "SJJ"),
    SKOPJE("Skopje", "SKP"),
    SOFIA("Sofia", "SOF"),
    STOCKHOLM("Sztokholm", "ARN"),
    WARSAW("Warszawa", "WAW"),
    VILNIUS("Wilno", "VNO"),
    VIENNA("Wiedeń", "VIE"),
    ZAGREB("Zagrzeb", "ZAG");


    companion object {
        fun filterByQuery(query: String): List<City> {
            return if (query.isNotEmpty()) {
                values()
                    .filter { it.cityName.contains(query, ignoreCase = true) }
                    .sortedBy { it.cityName }
            } else {
                values()
                    .sortedBy { it.cityName }

            }
        }

        fun getByCode(code: String): City? = City.values().find { it.iataCode == code }

    }
}
