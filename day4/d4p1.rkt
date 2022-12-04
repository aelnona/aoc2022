#lang racket
(require racket/cmdline)

(define (within line)
  (let* ([rangs (string-split line ",")]
         [a (string-split (car rangs) "-")]
         [b (string-split (car (cdr rangs)) "-")])
    ;(display (list rangs a b))
    (define (checkwithin a b)
      (let ([x (map string->number a)]
            [y (map string->number b)])
        (and (<= (first x) (first y))
             (>= (last x) (last y)))))
    (if (or (checkwithin a b)
            (checkwithin b a))
        1 0)))
    
(display
 (apply
  + (map within
         (string-split 
          (command-line #:args (lines) lines)
          "\n"))))
