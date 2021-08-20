(ns volley.events
  (:require [re-frame.core :refer [reg-event-db reg-event-fx] :as re-frame]
            [volley.db :as db]
            [clojure.string :as str]))


(reg-event-db
 :volley/initialize
 (fn [_ _]
   db/initial-db))

(reg-event-db
 :players/select
 (fn [db [_ player]]
   (assoc db :selected-player player)))

(reg-event-db
 :scores/inc
 (fn [{:keys [selected-player] :as db} [_ score-key]]
   (update-in db [:scores selected-player score-key] inc)))

(reg-event-fx
 :scores/download
 (fn [{:keys [db]} _]
   (let [csv-text (str (->> (:scores db)
                            (sort-by first)
                            (map (fn [[p-name p-scores]]                                  
                                   (str p-name "," (str/join "," (map p-scores db/scores-keys)))))
                            (str/join "\n")
                            (str "nombre," (str/join "," (map name db/scores-keys)) "\n")))]
     {:csv/download csv-text})))

(re-frame/reg-fx
 :csv/download
 (fn [csv-text]
   (let [download-anchor (js/document.createElement "a")]
     (.setAttribute download-anchor "href" (str "data:text/csv;charset=utf-8," (js/encodeURIComponent csv-text)))
     (.setAttribute download-anchor "download" "partido.csv")
     (set! (-> download-anchor .-style .-display) "none")
     (js/document.body.appendChild download-anchor)
     (.click download-anchor)
     (js/document.body.removeChild download-anchor))))
