class Proceso < ApplicationRecord
  belongs_to :modalidad
  belongs_to :user
  belongs_to :tipocontrato
end
