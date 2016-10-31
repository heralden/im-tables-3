(ns im-tables.views.table.core
  (:require [re-frame.core :refer [subscribe dispatch]]
            [im-tables.views.table.head.main :as table-head]
            [im-tables.views.table.body.main :as table-body]))

(defn main []
  (let [pagination (subscribe [:settings/pagination])]
    (fn [{:keys [results columnHeaders views] :as response}]
      [:table.table.table-striped.table-condensed
       [:thead
        (into [:tr]
              (map-indexed (fn [idx h]
                             [table-head/header {:header h
                                                 :view   (get views idx)}])
                           columnHeaders))]
       (into [:tbody]
             (map (fn [r]
                    [table-body/table-row r])
                  (take (:limit @pagination) (drop (:start @pagination) results))))])))