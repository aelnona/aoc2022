#lang racket
(require racket/cmdline)

(let* ([args (string-split
             (command-line
              #:args (lines)
              lines)
             "\n\n")] ; split on empty line
       [startcrates (first args)]
       [instructionlist (string-split (last args) "\n") ])

  ; define list of 9 lists, one for each stack

  ; parse instructions

  ; for each instruction, get numbers
  ;   first number (a) defines how many iterations
  ;   second number (b) defines start stack
  ;   end number (c) defines end stack

  ; for(0 to a) move one crate from b to c
