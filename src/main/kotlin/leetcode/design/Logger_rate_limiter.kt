class Logger() {

    private val history = mutableMapOf<String, Long>()

    /**
     * Runtime 351 ms Beats 94.29%
     * Memory 58 MB Beats 42.86%
     */
    fun shouldPrintMessage(timestamp: Int, message: String): Boolean {
        val previousMessageTime = history.get(message) ?: -10
        if (previousMessageTime <= (timestamp - 10L)) {
            history.put(message, timestamp.toLong())
            return true
        } else return false
    }

}
