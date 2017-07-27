package prosolupov.konstantin.ru.model

/**
 * Created by home on 26.06.2017.
 */
class Notes {

    var id: Int = 0;
    var title: String = "";
    var body: String = "";

    constructor(title: String, body: String){
        this.title = title
        this.body = body
    }
    constructor()

}

/*data class Notes(
    var id: Int,
    var title: String,
    var body: String
)*/
