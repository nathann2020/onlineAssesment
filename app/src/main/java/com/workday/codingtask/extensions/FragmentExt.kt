package com.workday.codingtask.extensions

import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 * @CreatedBy Nathan N
 *
 *         Author Email: Nathan.nakhjavani@gmail.com
 *         Created on: 2020-09-27
 */


/**
 * An Extension function for cleaning the boiler plate code of the static newInstance method in each
 * fragment. All required fields must be added in the lambda method passed as the parameter
 *
 * <pre>
 * LoginFragment.newInstance(firstName: String, age: Int) = LoginFragment().withArgs {
 *     putString(BUNDLE_KEY_NAME, firstName)
 *     putInt(BUNDLE_KEY_AGE, age)
 * }
 * </pre>
 */
inline fun <T : Fragment> T.withArgs(argsBuilder: Bundle.() -> Unit): T {
    return this.apply {
        arguments = Bundle().apply(argsBuilder)
    }
}