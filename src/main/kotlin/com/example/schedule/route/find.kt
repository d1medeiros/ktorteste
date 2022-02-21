package com.example.schedule


import com.example.schedule.api.ScheduleAPI
import com.example.schedule.model.Schedule
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.css.*
import kotlinx.html.*



suspend inline fun ApplicationCall.respondCss(builder: CssBuilder.() -> Unit) {
    this.respondText(CssBuilder().apply(builder).toString(), ContentType.Text.CSS)
}

fun Route.findSchedule() {
    get("/styles.css") {
        call.respondCss {
            body {
                color = Color.white
                width = 310.px
            }
            rule("div.title") {
                margin(15.px)
                padding(5.px)
                backgroundColor = Color.darkBlue
                color = Color.white
            }
            rule("div.page") {
                margin(15.px)
                backgroundColor = Color.red
            }
            rule("div.col-desc") {
                backgroundColor = Color.lightGray
            }
        }
    }

    get("/schedule") {
        val scheduleAPI = ScheduleAPI()
        val name = "lista de eventos"
        val listOfSchedule = scheduleAPI.defaultList
        call.respondHtml(HttpStatusCode.OK) {
            head {
                myHeader(name)
            }
            body {
                title(name)
                div(classes = "page") {
                    listOfEvents(listOfSchedule)
                }
            }
        }
    }
}

private fun HEAD.myHeader(name: String) {
    link(rel = "stylesheet", href = "/styles.css", type = "text/css")
    link(
        href = "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css",
        rel = "stylesheet"
    )
    script(
        type = ScriptType.textJavaScript,
        src = "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
    ) {
        unsafe {
        }
    }
    meta(name = "viewport") {
        content = "width=device-width, initial-scale=1"
    }
    title {
        +name
    }
}

private fun BODY.title(title: String) {
    div("title text-md-start") {
        +title
    }
}

private fun DIV.listOfEvents(listOfSchedule: MutableList<Schedule>) {
    div("container") {
        div("row row-cols-1") {
            listOfSchedule.forEach {
                div("col text-md-start") {
                    div("accordion") {
                        id = "accordionExample"
                        div("accordion-item") {
                            listOfEventHeader(it)
                            listOfEventBody(it)
                        }
                    }
                }
            }
        }
    }
}

private fun DIV.listOfEventBody(schedule: Schedule) {
    div("accordion-collapse collapse show") {
        id = "collapseOne-${schedule.id}"
        attributes["aria-labelledby"] = "headingOne-${schedule.id}"
        attributes["data-bs-parent"] = "#accordionExample"
        div("accordion-body") {
            div("container") {
                div("row row-cols-1") {
                    div("col col-desc") {
                        +"${schedule.label} ${schedule.description?:""}"
                    }
                    div("col text-center") {
                        button(classes = "btn btn-sm btn-primary") {
                            +"adicionar"
                        }
                        button(classes = "btn btn-sm btn-danger") {
                            disabled = true
                            +"remover"
                        }
                    }
                }
            }
        }
    }
}

private fun DIV.listOfEventHeader(schedule: Schedule) {
    h2("accordion-header") {
        id = "headingOne-${schedule.id}"
        button(classes = "accordion-button", type = ButtonType.button) {
            attributes["data-bs-toggle"] = "collapse"
            attributes["data-bs-target"] = "#collapseOne-${schedule.id}"
            attributes["aria-expanded"] = "true"
            attributes["aria-controls"] = "collapseOne"
            +schedule.label
        }
    }
}

