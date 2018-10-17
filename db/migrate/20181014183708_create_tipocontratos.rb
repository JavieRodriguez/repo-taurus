class CreateTipocontratos < ActiveRecord::Migration[5.2]
  def change
    create_table :tipocontratos do |t|
      t.string :tipo

      t.timestamps
    end
  end
end
