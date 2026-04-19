# Spring Boot　CRUDアプリ

## 概要
Spring Bootを用いて作成した、名前を管理するシンプルなCRUDアプリです。

## 機能
・名前の追加 (Create)
・名前の一覧表示 (Read)
・名前の編集 (Update)
・名前の削除 (Delete)

## 画面
・一覧画面 (名前の表示・削除・編集)
・編集画面 (名前の更新)

## 使用技術
・Java
・Spring Boot
・H2 Database
・Thymeleaf

## 処理の流れ (例：更新機能)
編集ボタンを押すと、ControllerでIDを受け取り、Serviceを通してDBから対象データを取得し、編集画面に表示します。
更新ボタン押下時は、Controllerで入力値を受け取り、ServiceでDBのデータを更新し、一覧画面にリダイレクトして反映させています。

## 工夫した点
削除や更新後はリダイレクトを使用し、画面表示の不具合を防ぎました。
