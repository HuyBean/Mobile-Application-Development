import android.util.Log
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable
import java.io.File

@Serializable
data class Student(
    val name: String,
    val classroom: String,
    val birthday: String,
    val gender: String
) {
    fun toJson(): String {
        return Json.encodeToString(serializer(), this)
    }

    companion object {
        fun fromJson(json: String): Student {

            return Json.decodeFromString(serializer(), json)
        }

        // Hàm ghi dữ liệu vào file JSON
        fun writeToFile(filePath: String, student: Student) {
            val json = Json.encodeToString(serializer(), student)
            File(filePath).writeText(json)
            Log.d("WriteToFile", "Data has been written to JSON file successfully")
        }
    }
}
