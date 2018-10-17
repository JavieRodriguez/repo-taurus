json.extract! actividad, :id, :descripcion, :etapa_id, :created_at, :updated_at
json.url actividad_url(actividad, format: :json)
