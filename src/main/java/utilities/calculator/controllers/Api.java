package utilities.calculator.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utilities.calculator.exceptions.HugeNumberException;
import utilities.calculator.model.Calc;
import utilities.calculator.services.Operations;

@RestController
public class Api {
    @GetMapping("/getcalculator/num1{num1}/num2{num2}/operation{operation}")
    public int calculator(@PathVariable("num1") int num1, @PathVariable("num2") int num2, @PathVariable("operation") String operation) {
        Operations operations = new Operations();

        switch (operation) {
            case "sum":
                return operations.sum(num1, num2);
            case "sub":
                return operations.sub(num1, num2);
            case "mult":
                return operations.mult(num1, num2);
            case "div":
                return operations.div(num1, num2);
            default:
                return 0;
        }
    }

    @GetMapping("/getcalculatorString/num1{num1}/num2{num2}/operation{operation}")
    @ExceptionHandler({HugeNumberException.class})
    public String calculatorString(@PathVariable("num1") int num1, @PathVariable("num2") int num2, @PathVariable("operation") String operation) throws Exception {
        Operations operations = new Operations();
        String result;
        switch (operation) {
            case "sum":
                result = String.valueOf(operations.sum(num1, num2));
                break;
            case "sub":
                result = String.valueOf(operations.sub(num1, num2));
                break;
            case "mult":
                result = String.valueOf(operations.mult(num1, num2));
                break;
            case "div":
                result = String.valueOf(operations.div(num1, num2));
                break;
            case "":
                result = "Please chose a Operation";
                break;
            default:
                return operation + " is not a Valid Operation.";
        }
        return result;
    }
    @GetMapping("/calc")
    @ExceptionHandler({HugeNumberException.class})
    public ResponseEntity<Calc> calc(@RequestBody Calc calc) throws Exception {
        Operations operations = new Operations();
        String result;
        switch (calc.getOperation()) {
            case "sum":
                calc.setResult(operations.sum(calc.getNum1(), calc.getNum2()));
                calc.setMessage("Operation Successfully Concluded!");
                break;
            case "sub":
                calc.setResult(operations.sub(calc.getNum1(), calc.getNum2()));
                calc.setMessage("Operation Successfully Concluded!");
                break;
            case "mult":
                calc.setResult(operations.mult(calc.getNum1(), calc.getNum2()));
                calc.setMessage("Operation Successfully Concluded!");
                break;
            case "div":
                calc.setResult(operations.div(calc.getNum1(), calc.getNum2()));
                calc.setMessage("Operation Successfully Concluded!");
                break;
            case "":
                calc.setMessage("Please chose a Operation");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(calc);
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(calc);
        }
        return ResponseEntity.status(HttpStatus.OK).body(calc);
    }
}