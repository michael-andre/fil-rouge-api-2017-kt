package fr.centralesupelec.sio.endpoints.utils

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import fr.centralesupelec.sio.model.Error
import java.io.IOException
import javax.servlet.http.HttpServletResponse

/**
 * Serialize an object to JSON using Gson, and write it to the response.
 * @param response: The object to serialize.
 * @throws IOException if the response cannot be written.
 */
// This syntax (out of a class) defines an extension method.
// The method will be "added" to any HttpServletResponse instance directly.
fun HttpServletResponse.writeJson(response: Any) {
    // This is strictly equivalent to setContentType(...)
    contentType = "application/json"
    // TODO: Don't recreate a new Gson instance for each response, reuse
    val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    gson.toJson(response, writer)
}

/**
 * Write a JSON error to the response. The structure will match the [Error] class.
 * @param reason: The error message.
 * @param status: The HTTP status of the response.
 * @throws IOException if the response cannot be written
 */
// In Kotlin, you can define optional parameters with default value.
fun HttpServletResponse.writeError(reason: String, status: Int = HttpServletResponse.SC_BAD_REQUEST) {
    this.status = status
    writeJson(Error(error = reason))
}