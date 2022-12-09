#lang racket
(require racket/cmdline)

(define (make-pos x y) (cons x y))
(define (get-x pos) (car pos))
(define (get-y pos) (cdr pos))
(define (proc-pos proc posa posb) (make-pos (proc (get-x posa) (get-x posb))
                                      (proc (get-y posa) (get-y posb))))
(define (add-pos posa posb) (proc-pos + posa posb))
(define (sub-pos posa posb) (proc-pos - posa posb))

(define (make-rope head tail) (cons head tail))
(define (get-head rope) (car rope))
(define (get-tail rope) (cdr rope))
(define (move-rope rope vec)
  ; Moves rope head
  (define (move-head)
    (add-pos (get-head rope) vec))
  (let ([head (move-head)]
        [tail (get-tail rope)])
    ; Moves rope tail
    (define (move-tail)
      (define (check-far get-? get-!) (and (= (- (get-? head) (get-? tail)) 2)
                                           (= (get-! head) (get-! tail))))
      (let ([relhead (sub-pos head tail)])
        (define dia?
          (or (= (abs (get-x relhead)) 2)
              (= (abs (get-y relhead)) 2)))
        (define move-dia
          (add-pos tail
                   (make-pos (if (< 0 (get-x relhead))
                                 1
                                 (- 1))
                             (if (< 0 (get-y relhead))
                                 1
                                 (- 1)))))
        (cond
          ;; If 2 in cardinal directions
          ; If tail x is 2 less than head x, and tail y = head y
          ; or tail y is 2 less than head y, and tail x = head x
          [(or (check-far get-x get-y) (check-far get-y get-x))
           (begin (display 'far) (add-pos tail vec))]
          ;; If 2 in diagonal.
          ; If head is a knight, bishop to it.
          [dia? (begin (display 'dia) move-dia)]
          [else (begin (display 'non) tail)]
          )))
  
    ; Return new rope
    (make-rope 
     (move-head)
     (move-tail)
     )))

(let ([insvec
       ; Convert instructions to vectors
       (apply append
              (map (lambda (insns)
                     (let* ([ins (string-split insns)]
                            [dir (list-ref ins 0)]
                            [num (string->number (list-ref ins 1))]
                            [numnum (/ num num)])
                       (map (lambda (i)
                              (make-pos (cond [(or (string=? dir "U")
                                                   (string=? dir "D")) 0]
                                              [(string=? dir "L") (- numnum)]
                                              [(string=? dir "R") numnum])
                                        (cond [(or (string=? dir "L")
                                                   (string=? dir "R")) 0]
                                              [(string=? dir "D") (- numnum)]
                                              [(string=? dir "U") numnum])))
                            (range 0 num))))
                   (string-split (command-line #:args (lines) lines) "\n")))])
  
  ; print instruction vectors
  (for-each displayln insvec)
  (newline)
  
  ; Set start position of rope (0,0)
  (let ([rope (make-rope
               (make-pos 0 0)
               (make-pos 0 0))])
    (define (movec rope veclist)
      ; Recursively moves the rope according to a list of vecs
      ; (I think Racket does provide 'vector' but... whatever)
      (if (null? veclist)
          rope
          (begin
            (pretty-display rope)
            (movec (move-rope rope (first veclist))
                   (rest veclist)))))
    (movec rope insvec)
  ))
