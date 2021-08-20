(ns volley.views
  (:require [re-frame.core :refer [dispatch subscribe]]
            [reagent.core :as reagent]
            [clojure.string :as str]
            [goog.string :as gstr]
            [volley.db :as db]))

(defn players []
  (let [players @(subscribe [:players/names])
        sel-player @(subscribe [:players/selected])]
    [:div.players
     (for [p players]
       ^{:key p}
       [:div.player.unselectable {:on-click #(dispatch [:players/select p])
                     :class (when (= p sel-player) "selected")
                                  :style {:background-color (db/players p)
                                          :color :white}} p])]))

(defn action-button [anim k]  
  (let [ss @(subscribe [:selected-player/scores])
        label {:saque-errado  "Saque errado"
               :saque-punto   "Saque punto"
               :pase-pp       "Pase ++"
               :pase-p        "Pase +" 
               :pase-n        "Pase -" 
               :pase-nn       "Pase --" 
               :ataque-punto "Ataque Punto"
               :bloqueo      "Bloqueo"}
        format-score (fn [sk]
                       [:div [:div (label sk)]
                        [:div.counter (str "(" (get ss sk) ")")]])]
    [:div.action {:on-click #(do
                               (dispatch [:scores/inc k])
                               (reset! anim k))
                  :on-animation-end #(reset! anim nil)
                  :class (when (= @anim k) " animate")
                  :style {:grid-area (name k)}}
     (format-score k)]))

(defn actions []  
  (let [anim (reagent/atom nil)]
    (fn []      
      [:div.actions.unselectable
       [action-button anim :saque-errado]
       [action-button anim :saque-punto]
       [action-button anim :pase-pp]
       [action-button anim :pase-p]
       [action-button anim :pase-n]
       [action-button anim :pase-nn]
       [action-button anim :ataque-punto]
       [action-button anim :bloqueo]])))

(defn main []
  [:div.main
   [players]
   [actions]
   [:div.download {:on-click #(dispatch [:scores/download])} "Descargar"]])
