package com.mvs.service

import io.ktor.http.HttpStatusCode
import io.bkbn.kompendium.models.meta.MethodInfo
import io.bkbn.kompendium.models.meta.RequestInfo
import io.bkbn.kompendium.models.meta.ResponseInfo

object DocExample {
  val getExamples = MethodInfo.GetInfo<Unit, ExampleResponse>(
    summary = "Example Parameters",
    description = "A test for setting parameter examples",
    responseInfo = ResponseInfo(
      status = HttpStatusCode.OK,
      description = "nice",
      examples = mapOf("test" to ExampleResponse(c = "spud"))
    ),
    canThrow = setOf(Exception::class)
  )
  @Suppress("MagicNumber")
  val postExamples = MethodInfo.PostInfo<ExampleParams, ExampleRequest, ExampleResponse>(
    summary = "Full Example",
    description = "Throws just about all Kompendium has to offer into one endpoint",
    requestInfo = RequestInfo(
      description = "Necessary deetz",
      examples = mapOf(
        "Send This" to ExampleRequest(ExampleNested("potato"), 13.37, listOf(12341))
      )
    ),
    responseInfo = ResponseInfo(
      status = HttpStatusCode.Created,
      description = "Congratz you hit da endpoint",
      examples = mapOf(
        "Expect This" to ExampleResponse(c = "Hi"),
        "Or This" to ExampleResponse(c = "Hey")
      )
    ),
    canThrow = setOf(Exception::class)
  )

  val idGetInfo = MethodInfo.GetInfo<ExampleParams, ExampleGeneric<Int>>(
    summary = "Get Test",
    description = "Test for the getting",
    tags = setOf("test", "sample", "get"),
    responseInfo = ResponseInfo(
      status = HttpStatusCode.OK,
      description = "Returns sample info"
    )
  )
  val singleGetInfo = MethodInfo.GetInfo<Unit, ExampleResponse>(
    summary = "Another get test",
    description = "testing more",
    tags = setOf("anotherTest", "sample"),
    responseInfo = ResponseInfo(
      status = HttpStatusCode.OK,
      description = "Returns a different sample"
    )
  )
  val singleGetInfoWithThrowable = singleGetInfo.copy(
    summary = "Show me the error baby 🙏",
    canThrow = setOf(Exception::class)
  )
  val singlePostInfo = MethodInfo.PostInfo<Unit, ExampleRequest, ExampleCreatedResponse>(
    summary = "Test post endpoint",
    description = "Post your tests here!",
    requestInfo = RequestInfo(
      description = "Simple request body"
    ),
    responseInfo = ResponseInfo(
      status = HttpStatusCode.Created,
      description = "Worlds most complex response"
    )
  )
  val singlePutInfo = MethodInfo.PutInfo<JustQuery, ExampleRequest, ExampleCreatedResponse>(
    summary = "Test put endpoint",
    description = "Put your tests here!",
    requestInfo = RequestInfo(
      description = "Info needed to perform this put request"
    ),
    responseInfo = ResponseInfo(
      status = HttpStatusCode.Created,
      description = "What we give you when u do the puts"
    )
  )
  val singleDeleteInfo = MethodInfo.DeleteInfo<Unit, Unit>(
    summary = "Test delete endpoint",
    description = "testing my deletes",
    responseInfo = ResponseInfo(
      status = HttpStatusCode.NoContent,
      description = "Signifies that your item was deleted successfully",
      mediaTypes = emptyList()
    )
  )
  val authenticatedSingleGetInfo = MethodInfo.GetInfo<Unit, Unit>(
    summary = "Another get test",
    description = "testing more",
    tags = setOf("anotherTest", "sample"),
    responseInfo = ResponseInfo(
      status = HttpStatusCode.OK,
      description = "Returns a different sample"
    ),
    securitySchemes = setOf("basic")
  )
  val undeclaredFields = MethodInfo.GetInfo<Unit, SimpleYetMysterious>(
    summary = "Tests adding undeclared fields",
    description = "vvv mysterious",
    tags = setOf("mysterious"),
    responseInfo = ResponseInfo(
      status = HttpStatusCode.OK,
      description = "good tings"
    )
  )
}
