package ovh.kevinsamyn.fastpoll.mappers

/**
 *
 */
abstract class AbstractBaseMapper<SOURCE, CIBLE, OPTIONS> {
    abstract fun mapMainFields(source: SOURCE): CIBLE
    abstract fun CIBLE.mapOptionalsFields(source: SOURCE, options: OPTIONS): CIBLE


    fun map(source: SOURCE, options: OPTIONS?): CIBLE {

        val result = mapMainFields(source)

        if (null != options) {
            result.mapOptionalsFields(source, options)
        }

        return result
    }
    /**
     * Mapping d'une liste.
     */
    fun map(sources : Collection<SOURCE>, options: OPTIONS?): Collection<CIBLE> {
        val cibles = ArrayList<CIBLE>()
        for (source in sources) {
            val element = map(source, options)
            if (null != element) {
                cibles.add(element)
            }
        }
        return cibles
    }

}
