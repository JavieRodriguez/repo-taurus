class ApplicationController < ActionController::Base

    def after_sign_in_path_for(resource)
        modalidads_path
      end
end
