(ns volley.db)

(def scores-keys [:saque-errado :saque-punto
                  :pase-pp :pase-p :pase-n :pase-nn
                  :ataque-punto :bloqueo])

(def empty-scores (zipmap scores-keys (repeat 0)))
(def players {"cam.p"  "#f94144"
              "camila" "#f3722c"
              "caro"   "#f8961e"
              "emilia" "#f9844a"
              "fer"    "#90be6d"
              "flor"   "#43aa8b"              
              "irene"  "#4d908e"
              "jose"   "#577590"
              "lara"   "#277da1"
              "lu"     "#ef476f"
              "sofia"  "#118ab2"
              "tati"   "#06d6a0"
              "toia"   "#3d405b"
              "vicky"  "#81b29a"
              "yaz"    "#d90429"})

(def initial-db
  {:selected-player "cam.p"
   :scores (zipmap (keys players) (repeat empty-scores))})
