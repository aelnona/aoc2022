#lang racket
(require racket/cmdline)

(define keylen 14)
(let ([instream (command-line #:args (args) args)])
  (define (searc instream)
    (define (searc-iter key i)
      (if (or (> i (string-length instream))
              (>= (length (remove-duplicates (string->list key))) keylen))
          i
          (searc-iter (substring instream i (+ i keylen))
                      (+ i 1))))
    (+ (searc-iter (substring instream 0 keylen) 1)
       (- keylen 1)))
  (searc instream))
       
