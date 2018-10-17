class CreateActividads < ActiveRecord::Migration[5.2]
  def change
    create_table :actividads do |t|
      t.string :descripcion
      t.references :etapa, foreign_key: true

      t.timestamps
    end
  end
end
