# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 2018_10_14_183708) do

  # These are extensions that must be enabled in order to support this database
  enable_extension "plpgsql"

  create_table "actividads", force: :cascade do |t|
    t.string "descripcion"
    t.bigint "etapa_id"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["etapa_id"], name: "index_actividads_on_etapa_id"
  end

  create_table "etapas", force: :cascade do |t|
    t.string "nombre"
    t.bigint "modalidad_id"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["modalidad_id"], name: "index_etapas_on_modalidad_id"
  end

  create_table "modalidads", force: :cascade do |t|
    t.string "nombre"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "procesos", force: :cascade do |t|
    t.string "numero"
    t.string "palabraclave"
    t.text "objeto"
    t.decimal "presupuesto"
    t.decimal "plazo"
    t.string "unidadplazo"
    t.bigint "tipocontrato_id"
    t.bigint "modalidad_id"
    t.bigint "user_id"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["modalidad_id"], name: "index_procesos_on_modalidad_id"
    t.index ["tipocontrato_id"], name: "index_procesos_on_tipocontrato_id"
    t.index ["user_id"], name: "index_procesos_on_user_id"
  end

  create_table "tipocontratos", force: :cascade do |t|
    t.string "tipo"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "users", force: :cascade do |t|
    t.string "email", default: "", null: false
    t.string "encrypted_password", default: "", null: false
    t.string "reset_password_token"
    t.datetime "reset_password_sent_at"
    t.datetime "remember_created_at"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.string "name"
    t.string "confirmation_token"
    t.datetime "confirmed_at"
    t.datetime "confirmation_sent_at"
    t.string "unconfirmed_email"
    t.integer "role"
    t.index ["email"], name: "index_users_on_email", unique: true
    t.index ["reset_password_token"], name: "index_users_on_reset_password_token", unique: true
  end

  add_foreign_key "actividads", "etapas"
  add_foreign_key "etapas", "modalidads"
  add_foreign_key "procesos", "modalidads"
  add_foreign_key "procesos", "users"
end
