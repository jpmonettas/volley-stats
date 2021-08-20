(ns volley.subs
  (:require [re-frame.core :refer [reg-sub]]))
         
(reg-sub
 :players/names
 (fn [db _]
   (-> db
       :scores
       keys
       sort)))

(reg-sub
 :players/selected
 (fn [db _]
   (:selected-player db)))

(reg-sub
 :selected-player/scores
 (fn [{:keys [selected-player scores]} _]
   (get scores selected-player)))
