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

  (define (exec-ins ins cratelist)
    (let ([numcrates (string->number (first ins))]
          [source (- (string->number (first (rest ins))) 1)]
          [dest (- (string->number (last (rest ins))) 1)])
      (define (move i slist dlist)
        (if (<= i 0)
            (cons slist dlist)
            (move (- i 1)
                  (rest slist)
                  (cons (first slist) dlist))))
      (let* ([modiff
             (move numcrates
                   (list-ref cratelist source)
                   (list-ref cratelist dest))]
             [newsource (car modiff)]
             [newdest (cdr modiff)])
        (map (lambda (index)
               (cond [(= index source) newsource]
                     [(= index dest) newdest]
                     [else (list-ref cratelist index)]))
             (range 0 9)))))

  (define (run inslst cratelist)
    (if (null? inslst)
        cratelist
        (run (rest inslst)
             (exec-ins (first inslst) cratelist))))
  (let ([don (run instructionlist cratelist)])
    (pretty-display don)
    (let ()
      (for-each (lambda (x) (display (first x))) don)))
  (newline))
