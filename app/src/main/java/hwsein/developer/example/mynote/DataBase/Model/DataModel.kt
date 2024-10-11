package hwsein.developer.example.mynote.DataBase.Model

data class DataModel(
    var id : Int ,
    var title : String ,
    var detail : String ,
    var detail2 : String ,
    var detail3 : String ,
    var image : ByteArray? ,
    var image2 : ByteArray? ,
    var draw : ByteArray? ,
    val state : String
)