#lang racket
(require racket/cmdline)

(apply +
       (map 
        (lambda (line)
          (define (checkwithin a b)
            (let ([x (map string->number a)]
                  [y (map string->number b)])
              (and (>= (first x) (first y))
                   (<= (first x) (last y)))))
          (let* ([rangs (string-split line ",")]
                 [a (string-split (first rangs) "-")]
                 [b (string-split (last rangs) "-")])
            (if (or (checkwithin a b)
                    (checkwithin b a))
                1 0)))
        (string-split
         ; pass input as command line argument,
         ; because I cannot be asked to do files
         ; 
         ; $ racket d4p2.rkt "$(cat day4input.txt)"
         (command-line #:args (lines) lines)
         "\n")))
