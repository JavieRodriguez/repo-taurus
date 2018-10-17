class CreateProcesos < ActiveRecord::Migration[5.2]
  def change
    create_table :procesos do |t|
      t.string :numero
      t.string :palabraclave
      t.text :objeto
      t.numeric :presupuesto
      t.numeric :plazo
      t.string :unidadplazo
      t.references :tipocontrato
      t.references :modalidad, foreign_key: true
      t.references :user, foreign_key: true

      t.timestamps
    end
  end
end
