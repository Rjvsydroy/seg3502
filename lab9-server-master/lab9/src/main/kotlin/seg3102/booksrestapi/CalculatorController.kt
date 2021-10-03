package seg3102.booksrestapi

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("calculator")
class CalculatorController {
    @GetMapping("/add/{nombre1}/{nombre2}")
    fun add(@PathVariable nombre1: Double,@PathVariable nombre2: Double)= nombre1 + nombre2
    @GetMapping("/substract/{nombre1}/{nombre2}")
    fun substract(@PathVariable nombre1: Double,@PathVariable nombre2: Double)= nombre1 - nombre2
    @GetMapping("/multiply/{nombre1}/{nombre2}")
    fun multiply(@PathVariable nombre1: Double,@PathVariable nombre2: Double)= nombre1 * nombre2
    @GetMapping("/divide/{nombre1}/{nombre2}")
    fun divide(@PathVariable nombre1: Double,@PathVariable nombre2: Double)= nombre1 / nombre2
}