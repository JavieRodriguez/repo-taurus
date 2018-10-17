Rails.application.routes.draw do
  resources :tipocontratos
  resources :modalidads do
    resources :procesos do
      resources :etapas do
        resources :actividads
      end
    end
  end 
  
  root to: 'visitors#index'
  devise_for :users
end
