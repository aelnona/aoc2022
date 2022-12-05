#lang racket
(require racket/cmdline)

(define (delast lst) (reverse (cdr (reverse lst))))
(define (map-index proc lst)
  (map proc lst (range (length lst))))

(define (getcrateslice crateno line)
  (list-ref line (+ (* crateno 4) 1) ))

(let* ([args (string-split
              (command-line
              #:args (lines)
              lines)
             "\n\n")] ; split on empty line
       [startcratelist
        (map (lambda (lines)
               (map (lambda (x)
                      (let ([crate (getcrateslice x (string->list lines))])
                        (if
                         (not (char=? #\space crate))
                         crate
                         null)))
                    (range 0 9))
               )
             (string-split (first args) "\n"))]
       [cratelist  
        (map (lambda (i) (list-ref startcratelist i)
                (remove* (list null)
                         (map (lambda (j) (list-ref (list-ref startcratelist j) i))
                              (range 0 8))))
             (range 0 9))]
       [instructionlist
        (let ([ss (string-split (last args) "\n")])
          (map (lambda (lines)
                 (let ([sss (string-split lines " ")])
                   (list (list-ref sss 1)
                         (list-ref sss 3)
                         (list-ref sss 5))))
               ss))])
  (println 'Crates:)
  (pretty-display cratelist)
  (println 'Instructions:)
  (pretty-display instructionlist)
  (newline)

;  (pretty-display
;   (let ([cratelist 
;          (map (lambda (instruction)
;                 (let ([a (- (string->number (list-ref instruction 0)) 1)]
;                       [b (- (string->number (list-ref instruction 1)) 1)]
;                       [c (- (string->number (list-ref instruction 2)) 1)])
;                   (map (lambda (i)
;                          ; for 0 to a, move from b to c
;                          (cons
;                           (list-ref cratelist c)
;                           (list-ref cratelist b))
;                                )
;                        (range 0 a))
;                   ))
;               instructionlist)
;          ])
;     cratelist
  ;   ))

  (define (rec instructions cratelist)
    (if (null? (cdr instructions))
        cratelist
        (rec (rest instructions) 
  )
