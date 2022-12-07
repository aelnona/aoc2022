#lang racket
(require racket/cmdline)

(define (command? line) (string=? "$" (first line)))
(define (dir? line) (string=? "dir" (first line)))
(define (file? line) (string->number (first line)))

(let* ([linelist (string-split
                  (command-line #:args (in) in)
                  "\n")])
  (map (lambda (linein)
         (let ([line (string-split linein " ")])
           (cond [(command? line) 'command]
                 [(dir? line) 'dir]
                 [(file? line) 'file])))
       linelist))
  
