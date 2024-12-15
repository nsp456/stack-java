package com.example.stack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StackController {

    private CustomStack stack;

    public StackController(@Value("${stack.size}") int size) {
        this.stack = new CustomStack(size);
    }

    @PostMapping("/push")
    public ResponseEntity<Void> push(@RequestBody StackRequest request) {
        try {
            stack.push(request.getValue());
            return ResponseEntity.ok().build();
        } catch (StackOverflowException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/pop")
    public ResponseEntity<Void> pop() {
        try {
            stack.pop();
            return ResponseEntity.ok().build();
        } catch (StackUnderflowException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/stack")
    public ResponseEntity<int[]> getStack() {
        return ResponseEntity.ok(stack.getStack());
    }

    @PostMapping("/setSize")
    public ResponseEntity<Void> setSize(@RequestBody StackRequest request) {
        try {
            stack.setSize(request.getValue());
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/getSize")
    public ResponseEntity<Integer> getSize() {
        return ResponseEntity.ok(stack.getSize());
    }

    @GetMapping("/peek")
    public ResponseEntity<Integer> peek() {
        try {
            Integer topElement = stack.peek();
            return ResponseEntity.ok(topElement);
        } catch (StackUnderflowException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}